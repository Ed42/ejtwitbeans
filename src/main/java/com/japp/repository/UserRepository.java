package com.japp.repository;

import com.japp.domain.User;
import com.japp.domain.service.EtwitterUserDetailsService;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author mh
 * @since 02.04.11
 */
public interface UserRepository extends GraphRepository<User>, EtwitterUserDetailsService {
    User findByUsername(String username);
}
