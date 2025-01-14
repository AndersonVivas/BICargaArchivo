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
package ec.com.oncedev.service;

import ec.com.oncedev.model.OrderTransacccion;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class OrdenTransaccionService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<OrderTransacccion> memberEventSrc;

    
    public List<OrderTransacccion> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderTransacccion> criteria = cb.createQuery(OrderTransacccion.class);
        Root<OrderTransacccion> orderTransacccion = criteria.from(OrderTransacccion.class);
        criteria.select(orderTransacccion).orderBy(cb.asc(orderTransacccion.get("id")));
        return em.createQuery(criteria).getResultList();
    }
    
    
    public void register(OrderTransacccion orderTransacccion) throws Exception {
        log.info("Registering " + orderTransacccion.getIdentificacion());
        em.persist(orderTransacccion);
        memberEventSrc.fire(orderTransacccion);
    }
    
    public OrderTransacccion findById(Long id) {
        return em.find(OrderTransacccion.class, id);
    }
}
