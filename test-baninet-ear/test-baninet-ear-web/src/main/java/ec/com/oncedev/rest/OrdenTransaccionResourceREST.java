/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.com.oncedev.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.com.oncedev.model.OrderTransacccion;
import ec.com.oncedev.service.OrdenTransaccionService;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/ordentransaccion")
@RequestScoped
public class OrdenTransaccionResourceREST {
    @Inject
    private Logger log;

    @Inject
    private OrdenTransaccionService ordenTransaccionService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderTransacccion> listAllMembers() {
    	System.out.println("Ingreso al ENDPONIT ordenTransaccionService2");
        return ordenTransaccionService.findAllOrderedByName();
    }

}
