package org.gd.eventhub.Exceptions.notFounEX;

import org.gd.eventhub.Exceptions.ResourceNotFoundException;



public class EventNotFoundException extends ResourceNotFoundException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
