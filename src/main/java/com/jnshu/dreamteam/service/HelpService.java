package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.Help;

/**
 * @author draper_hxy
 */
public interface HelpService {

    Help select(Long id);

    Boolean insert(Help help);

    Boolean update(Help help);

    Boolean delete(Long id);

}
