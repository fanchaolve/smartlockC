package com.qeasy.samrtlockb.widget.multitype;

import com.qeasy.samrtlockb.widget.multitype.data.Item;

/**
 * Created by Administrator on 2016/11/22.
 */

public class ProviderNotFoundException extends RuntimeException {


    public ProviderNotFoundException(Class<? extends Item> clazz) {

        super("\"Do you have registered the provider for {className}.class in the adapter/pool?"
        .replace("{className}",clazz.getSimpleName()));
    }
}
