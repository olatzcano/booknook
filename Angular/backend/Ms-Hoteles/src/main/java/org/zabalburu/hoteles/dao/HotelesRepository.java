package org.zabalburu.hoteles.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zabalburu.hoteles.modelo.Hotel;

public interface HotelesRepository extends JpaRepository<Hotel, Integer> {

}
