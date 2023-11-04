package ${packageName}.dao.impl;

import ${packageName}.dao.${upperCase(module.name)}Dao;
import ${packageName}.entity.${upperCase(module.name)};
import org.springframework.stereotype.Repository;

/**
 * Dao - ${module.memo}
 * 
 * @author ${author}
 * @version ${version}
 */
@Repository
public class ${upperCase(module.name)}DaoImpl extends BaseDaoImpl<${upperCase(module.name)}, Long> implements ${upperCase(module.name)}Dao {

}