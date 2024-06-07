package com.ashish.mssc.beerordeservice.service;

import com.ashish.mssc.beerordeservice.domain.BeerOrder;
import com.ashish.mssc.beerordeservice.domain.BeerOrderEventEnum;
import com.ashish.mssc.beerordeservice.domain.BeerOrderStatusEnum;
import com.ashish.mssc.beerordeservice.repository.BeerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Configuration
public class OrderStatusChangeInterceptor extends
        StateMachineInterceptorAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;

    @Override
    public void preStateChange(State<BeerOrderStatusEnum, BeerOrderEventEnum> state,
                               Message<BeerOrderEventEnum> message,
                               Transition<BeerOrderStatusEnum, BeerOrderEventEnum> transition,
                               StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine,
                               StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> rootStateMachine) {
        //super.preStateChange(state, message, transition, stateMachine, rootStateMachine);
        Optional.ofNullable(UUID.class.cast(message.getHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, UUID.randomUUID())))
                .ifPresent( orderId -> {
                    BeerOrder beerOrder = beerOrderRepository.getReferenceById(orderId);
                    beerOrder.setOrderStatus(state.getId());
                    beerOrderRepository.saveAndFlush(beerOrder);
                });

    }
}
