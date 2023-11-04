
package ${packageName}.service.impl;

import ${packageName}.entity.${upperCase(module.name)};
import ${packageName}.service.${upperCase(module.name)}Service;
import org.springframework.stereotype.Service;

/**
 * Service - ${module.memo}
 * 
 * @author ${author}
 * @version ${version}
 */
@Service
public class ${upperCase(module.name)}ServiceImpl extends BaseServiceImpl<${upperCase(module.name)}, Long> implements ${upperCase(module.name)}Service {

}