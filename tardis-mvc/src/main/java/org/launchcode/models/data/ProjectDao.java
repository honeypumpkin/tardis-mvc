package org.launchcode.models.data;

import org.launchcode.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface ProjectDao extends CrudRepository<Project, Integer> {
}
