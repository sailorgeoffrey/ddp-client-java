/*
 * Copyright (C) 2014. Geoffrey Chandler.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.meteor.ddp.subscription;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Converts from json to objects based on the collection type that is registered.
 * <p/>
 * Created by gcc on 1/29/14.
 */
public class ObjectConverterJson implements ObjectConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectConverterJson.class);

    private static final boolean INFO = LOGGER.isInfoEnabled();

    private static final Gson GSON = new Gson();

    private final Map<String, Class> classMap = new ConcurrentHashMap<>();

    @Override
    public void register(final String subscriptionName,
                         final Class clazz) {

        if (INFO) LOGGER.info("registering " + clazz + " for subscription: " + subscriptionName);
        this.classMap.put(subscriptionName, clazz);
    }

    @Override
    public Object toObject(final JsonObject serializedObject,
                           final String collectionName) {

        final Class clazz = classMap.get(collectionName);

        final Object ret;
        if (clazz != null) {
            ret = GSON.fromJson(serializedObject, classMap.get(collectionName));
        } else {
            LOGGER.error("could not find registered class for deserialization: " + collectionName);
            ret = null;
        }

        return ret;
    }

    @Override
    public Object updateFields(Object record, Map<String, Object> fields) {
        //TODO: handle if this is a hashmap and get/set items
        final BeanMap beanMap = new BeanMap(record);
        for (final String key : fields.keySet()) {
            final Object thisField = fields.get(key);
            beanMap.put(key, thisField);
        }
        return beanMap.getBean();
    }
}
