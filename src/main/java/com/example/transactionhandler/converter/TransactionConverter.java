package com.example.transactionhandler.converter;

import com.example.transactionhandler.model.Transaction;
import com.example.transactionhandler.model.TransactionData;
import com.example.transactionhandler.model.dto.TransactionDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface TransactionConverter {

    @IterableMapping(qualifiedByName = "toDto")
    List<TransactionDto> toDtoList(List<Transaction> entities);

    @Named("toDto")
    @Mapping(source = "type", target = "type.typeName")
    @Mapping(source = "actor", target = "actor.actorName")
    @Mapping(source = "dto", target = "data", qualifiedByName = "fromMap")
    Transaction toEntity(TransactionDto dto);

    @Named("toDto")
    @Mapping(source = "type.typeName", target = "type")
    @Mapping(source = "actor.actorName", target = "actor")
    @Mapping(source = "entity", target = "data", qualifiedByName = "toMap")
    TransactionDto toDto(Transaction entity);

    @Named("toMap")
    default Map<String, String> toMap(Transaction entity) {
        return entity.getData().stream()
                .collect(Collectors.toMap(
                        data -> data.getCompositeId().getKey(),
                        TransactionData::getValue));
    }

    @Named("fromMap")
    default List<TransactionData> fromMap(TransactionDto dto) {
        return dto.getData().entrySet().stream()
                .map(entry -> {
                    TransactionData data;
                    data = new TransactionData();
                    data.setCompositeId(new TransactionData.TransactionDataId(dto.getId(), entry.getKey()));
                    data.setValue(entry.getValue());
                    return data;
                })
                .collect(Collectors.toList());
    }
}
