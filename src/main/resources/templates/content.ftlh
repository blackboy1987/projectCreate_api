
import javax.persistence.Entity;

@Entity
public class ${module.name} extends BaseEntity<Long> {
    <#list module.items as item>
    private ${item.type} ${item.filedName};
    </#list>
}
