package com.lyy.service;

import com.lyy.service.model.PromoModel;

public interface PromoService {
    //根据商品id获取对应的秒杀活动信息
    PromoModel getPromoByItemId(Integer itemId);
}
