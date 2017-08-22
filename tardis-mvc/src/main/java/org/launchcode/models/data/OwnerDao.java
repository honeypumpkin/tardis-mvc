package org.launchcode.models.data;

import org.launchcode.models.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface OwnerDao extends CrudRepository<Owner, Integer> {
}
