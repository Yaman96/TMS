package com.ymprog.tms.services;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ymprog.tms.entities.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
@JsonComponent
public class TaskListJsonResult extends JsonSerializer<TaskListJsonResult>{

    private List<Task> tasklist;

    @Override
    public void serialize(TaskListJsonResult value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
                gen.writeStartObject();
                gen.writeObjectField("tasklist", value.getTasklist());
                gen.writeEndObject();
    }
    
}
