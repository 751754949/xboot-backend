package cn.exrick.xboot.generator.bean;

import lombok.Data;

import java.util.Objects;

/**
 * @author Exrickx
 */
@Data
public class EntityOfEntity {

    private String entityPackage;

    private String daoPackage;

    private String servicePackage;

    private String serviceImplPackage;

    private String controllerPackage;

    private String author;

    private String className;

    private String classNameLowerCase;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityOfEntity that = (EntityOfEntity) o;
        return Objects.equals(entityPackage, that.entityPackage) &&
                Objects.equals(daoPackage, that.daoPackage) &&
                Objects.equals(servicePackage, that.servicePackage) &&
                Objects.equals(serviceImplPackage, that.serviceImplPackage) &&
                Objects.equals(controllerPackage, that.controllerPackage) &&
                Objects.equals(author, that.author) &&
                Objects.equals(className, that.className) &&
                Objects.equals(classNameLowerCase, that.classNameLowerCase) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(primaryKeyType, that.primaryKeyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityPackage, daoPackage, servicePackage, serviceImplPackage, controllerPackage, author, className, classNameLowerCase, tableName, description, primaryKeyType);
    }

    private String tableName;

    private String description;

    private String primaryKeyType;

}
