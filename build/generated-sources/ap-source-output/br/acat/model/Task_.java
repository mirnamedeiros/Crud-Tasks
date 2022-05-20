package br.acat.model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-05-20T17:41:15")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, String> responsible;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Boolean> finished;
    public static volatile SingularAttribute<Task, Integer> id;
    public static volatile SingularAttribute<Task, String> priority;
    public static volatile SingularAttribute<Task, Calendar> deadline;
    public static volatile SingularAttribute<Task, String> tittle;

}