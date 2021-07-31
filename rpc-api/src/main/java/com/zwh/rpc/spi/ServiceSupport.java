package com.zwh.rpc.spi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceSupport {

    private static final Map<String,Object> singletonServices = new HashMap<>();

    public synchronized static <S> S load(Class<S> serviceClass) {
        return StreamSupport.stream(ServiceLoader.load(serviceClass).spliterator(), false)
                .map(ServiceSupport::singletonFilter)
                .findFirst().orElseThrow(ServiceLoadException::new);
    }

    public synchronized static <S> Collection<S> loadAll(Class<S> serviceClass) {
        return StreamSupport.stream(ServiceLoader.load(serviceClass).spliterator(), false)
                .map(ServiceSupport::singletonFilter)
                .collect(Collectors.toList());
    }

    private static <S> S singletonFilter(S service) {
        if(service.getClass().isAnnotationPresent(Singleton.class)) {
            String serviceClass = service.getClass().getCanonicalName();
            S singletonService = (S)singletonServices.putIfAbsent(serviceClass, service);
            return singletonService==null ? service : singletonService;
        } else {
            return service;
        }
    }
}
