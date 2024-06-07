package com.ashish.mssc.beerordeservice.sm.action;

import com.ashish.common.model.events.ValidateOrderRequest;
import com.ashish.mssc.beerordeservice.config.JMSConfig;
import com.ashish.mssc.beerordeservice.domain.BeerOrder;
import com.ashish.mssc.beerordeservice.domain.BeerOrderEventEnum;
import com.ashish.mssc.beerordeservice.domain.BeerOrderStatusEnum;
import com.ashish.mssc.beerordeservice.repository.BeerOrderRepository;
import com.ashish.mssc.beerordeservice.service.BeerOrderManagerImpl;
import com.ashish.mssc.beerordeservice.web.mapper.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String orderId = (String) stateContext.getMessage().getHeaders()
                .get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.getReferenceById(UUID.fromString(orderId));

        jmsTemplate.convertAndSend(JMSConfig.VALIDATE_ORDER_QUEUE,
                ValidateOrderRequest.builder()
                        .beerOrderDto(beerOrderMapper.beerOrderToDto(beerOrder))
                        .build()
        );
        log.info("Message sent to validate order for " + orderId);
    }
}
