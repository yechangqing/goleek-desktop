package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectStock {

    private String id;
    private String name;
    private String code;
    final private Double margin = 1.;
    final private Integer unit = 100;
    final private Double min = 0.01;

    ObjectStock() {
        this.id = null;
        this.name = null;
        this.code = null;
    }

    boolean isValid() {
        return this.id != null && this.name != null && this.code != null;
    }

    void setInvalid() {
        this.id = null;
        this.name = null;
        this.code = null;
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    double getMargin() {
        return this.margin;
    }

    int getUnit() {
        return this.unit;
    }

    double getMin() {
        return this.min;
    }
}
