/**
 * WebQAServiceRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.net.webqa;

public class WebQAServiceRequestType  implements java.io.Serializable {
    private int typeNo;

    private java.lang.String name;

    private java.lang.String category;

    private java.lang.String keywords;

    public WebQAServiceRequestType() {
    }

    public WebQAServiceRequestType(
           int typeNo,
           java.lang.String name,
           java.lang.String category,
           java.lang.String keywords) {
           this.typeNo = typeNo;
           this.name = name;
           this.category = category;
           this.keywords = keywords;
    }


    /**
     * Gets the typeNo value for this WebQAServiceRequestType.
     * 
     * @return typeNo
     */
    public int getTypeNo() {
        return typeNo;
    }


    /**
     * Sets the typeNo value for this WebQAServiceRequestType.
     * 
     * @param typeNo
     */
    public void setTypeNo(int typeNo) {
        this.typeNo = typeNo;
    }


    /**
     * Gets the name value for this WebQAServiceRequestType.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this WebQAServiceRequestType.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the category value for this WebQAServiceRequestType.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this WebQAServiceRequestType.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the keywords value for this WebQAServiceRequestType.
     * 
     * @return keywords
     */
    public java.lang.String getKeywords() {
        return keywords;
    }


    /**
     * Sets the keywords value for this WebQAServiceRequestType.
     * 
     * @param keywords
     */
    public void setKeywords(java.lang.String keywords) {
        this.keywords = keywords;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebQAServiceRequestType)) return false;
        WebQAServiceRequestType other = (WebQAServiceRequestType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.typeNo == other.getTypeNo() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.keywords==null && other.getKeywords()==null) || 
             (this.keywords!=null &&
              this.keywords.equals(other.getKeywords())));
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
        _hashCode += getTypeNo();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getKeywords() != null) {
            _hashCode += getKeywords().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebQAServiceRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "WebQAServiceRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "typeNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keywords");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "keywords"));
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
