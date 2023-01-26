package com.backand.tracker.utils;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractDto implements Serializable {
    private Long id;

    private Date created;

    private Date update;

}
