package ${packageName}.entity;
<#noautoesc>
import javax.persistence.Entity;
<#if module.tableName?has_content>
import jakarta.persistence.Table;
</#if>
<#list importPackages as importPackage>
${importPackage};
</#list>

/**
* Entity - ${module.memo}
*
* @author ${author!}
* @version ${version!}
*/
@Entity
<#if module.tableName?has_content>
@Table(name = "${module.tableName}")
</#if>
public class ${upperCase(module.name)} extends BaseEntity<Long> {

    <#list module.items as item>
    <#if item.comment?has_content>
    /**
    * ${item.comment!}
    */
    </#if>
    private ${item.type} ${item.filedName};

    </#list>

    <#list module.items as item>
     <#if item.comment?has_content>
     /**
     * 获取${item.comment}
     *
     * @return ${item.comment}
     */
     </#if>
    public ${item.type} get${upperCase(item.filedName)}() {
        return ${item.filedName};
    }

    <#if item.comment?has_content>
    /**
    * 设置${item.comment}
    *
    * @param member
    *          ${item.comment}
    */
    </#if>
    public void set${upperCase(item.filedName)}(${item.type} ${item.filedName}) {
        this.${item.filedName} = ${item.filedName};
    }

    </#list>
}
</#noautoesc>