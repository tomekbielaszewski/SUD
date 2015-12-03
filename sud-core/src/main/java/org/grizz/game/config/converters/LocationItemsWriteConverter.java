package org.grizz.game.config.converters;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.grizz.game.model.impl.ItemStack;
import org.grizz.game.model.impl.LocationItemsEntity;
import org.grizz.game.model.items.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Grizz on 2015-11-27.
 */
@Service
public class LocationItemsWriteConverter implements Converter<LocationItemsEntity, DBObject> {
    @Autowired
    private ItemStackWriteConverter itemStackConverter;

    @Override
    public DBObject convert(LocationItemsEntity source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("_id", new ObjectId(source.getId()));
        dbo.put("locationId", source.getLocationId());
        dbo.put("staticItems", source.getStaticItems());
        dbo.put("mobileItems", convert(source.getMobileItems()));
        return dbo;
    }

    private List<DBObject> convert(List<Item> mobileItems) {
        Multiset<Item> items = HashMultiset.create();
        mobileItems.stream()
                .forEach(item -> items.add(item));
        List<DBObject> countedItems = items.elementSet().stream()
                .map(item -> ItemStack.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .amount(items.count(item))
                        .build())
                .map(itemStack -> itemStackConverter.convert(itemStack))
                .collect(Collectors.toList());
        return countedItems;
    }
}