/**
 * WebQAFilterItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.net.webqa;

public class WebQAFilterItem  implements java.io.Serializable {
    private int filterID;

    private int parentID;

    private java.lang.String description;

    public WebQAFilterItem() {
    }

    public WebQAFilterItem(
           int filterID,
           int parentID,
           java.lang.String description) {
           this.filterID = filterID;
           this.parentID = parentID;
           this.description = description;
    }


    /**
     * Gets the filterID value for this WebQAFilterItem.
     * 
     * @return filterID
     */
    public int getFilterID() {
        return filterID;
    }


    /**
     * Sets the filterID value for this WebQAFilterItem.
     * 
     * @param filterID
     */
    public void setFilterID(int filterID) {
        this.filterID = filterID;
    }


    /**
     * Gets the parentID value for this WebQAFilterItem.
     * 
     * @return parentID
     */
    public int getParentID() {
        return parentID;
    }


    /**
     * Sets the parentID value for this WebQAFilterItem.
     * 
     * @param parentID
     */
    public void setParentID(int parentID) {
        this.parentID = parentID;
    }


    /**
     * Gets the description value for this WebQAFilterItem.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this WebQAFilterItem.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebQAFilterItem)) return false;
        WebQAFilterItem other = (WebQAFilterItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.filterID == other.getFilterID() &&
            this.parentID == other.getParentID() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getFilterID();
        _hashCode += getParentID();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebQAFilterItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "WebQAFilterItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filterID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "filterID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "parentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
