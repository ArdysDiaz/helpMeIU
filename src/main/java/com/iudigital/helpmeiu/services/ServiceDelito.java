package com.iudigital.helpmeiu.services;

import com.iudigital.helpmeiu.models.Delito;

import java.util.Collection;

public interface ServiceDelito {
    Collection<Delito> getDelitos();
    Delito getDelito(Integer id);
    void saveDelito(Delito delito);
    void updateDelito(Integer id, Delito delito);
}
