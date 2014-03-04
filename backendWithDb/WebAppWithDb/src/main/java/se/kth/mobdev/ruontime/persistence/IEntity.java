package se.kth.mobdev.ruontime.persistence;

import java.io.Serializable;
import java.util.UUID;

/**
 * Interface marks class which can be persisted.
 *
 */
public interface IEntity extends Serializable {

    /**
     * Property which represents id.
     */
    String P_ID = "id";

    /**
     * Get primary key.
     *
     * @return primary key
     */
    Integer getId();

    /**
     * Set primary key.
     *
     * @param id primary key
     */
    void setId(Integer id);
}
