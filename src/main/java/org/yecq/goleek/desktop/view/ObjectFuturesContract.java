package org.yecq.goleek.desktop.view;

/**
 *
 * @author yecq
 */
class ObjectFuturesContract {

    private String id;
    private String contract;
    private String name;
    private Double margin;
    private Integer unit;
    private Double min;

    public ObjectFuturesContract() {
        this.id = null;
        this.contract = null;
        this.name = null;
        this.margin = null;
        this.unit = null;
        this.min = null;
    }

    boolean isValid() {
        return this.id != null && this.contract != null && this.margin != null && this.unit != null && this.min != null;
    }

    void setInvalid() {
        this.id = null;
        this.contract = null;
        this.name = null;
        this.margin = null;
        this.unit = null;
        this.min = null;
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getContract() {
        return contract;
    }

    void setContract(String contract) {
        this.contract = contract;
    }

    Double getMargin() {
        return margin;
    }

    void setMargin(Double margin) {
        this.margin = margin;
    }

    Integer getUnit() {
        return unit;
    }

    void setUnit(Integer unit) {
        this.unit = unit;
    }

    Double getMin() {
        return min;
    }

    void setMin(Double min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
