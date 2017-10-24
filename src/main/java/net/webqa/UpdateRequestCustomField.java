/**
 * UpdateRequestCustomField.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class UpdateRequestCustomField  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String referenceNo;

    private java.lang.String fieldName;

    private java.lang.String newFieldValue;

    public UpdateRequestCustomField() {
    }

    public UpdateRequestCustomField(
           java.lang.String authKey,
           java.lang.String referenceNo,
           java.lang.String fieldName,
           java.lang.String newFieldValue) {
           this.authKey = authKey;
           this.referenceNo = referenceNo;
           this.fieldName = fieldName;
           this.newFieldValue = newFieldValue;
    }


    /**
     * Gets the authKey value for this UpdateRequestCustomField.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this UpdateRequestCustomField.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the referenceNo value for this UpdateRequestCustomField.
     * 
     * @return referenceNo
     */
    public java.lang.String getReferenceNo() {
        return referenceNo;
    }


    /**
     * Sets the referenceNo value for this UpdateRequestCustomField.
     * 
     * @param referenceNo
     */
    public void setReferenceNo(java.lang.String referenceNo) {
        this.referenceNo = referenceNo;
    }


    /**
     * Gets the fieldName value for this UpdateRequestCustomField.
     * 
     * @return fieldName
     */
    public java.lang.String getFieldName() {
        return fieldName;
    }


    /**
     * Sets the fieldName value for this UpdateRequestCustomField.
     * 
     * @param fieldName
     */
    public void setFieldName(java.lang.String fieldName) {
        this.fieldName = fieldName;
    }


    /**
     * Gets the newFieldValue value for this UpdateRequestCustomField.
     * 
     * @return newFieldValue
     */
    public java.lang.String getNewFieldValue() {
        return newFieldValue;
    }


    /**
     * Sets the newFieldValue value for this UpdateRequestCustomField.
     * 
     * @param newFieldValue
     */
    public void setNewFieldValue(java.lang.String newFieldValue) {
        this.newFieldValue = newFieldValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateRequestCustomField)) return false;
        UpdateRequestCustomField other = (UpdateRequestCustomField) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authKey==null && other.getAuthKey()==null) || 
             (this.authKey!=null &&
              this.authKey.equals(other.getAuthKey()))) &&
            ((this.referenceNo==null && other.getReferenceNo()==null) || 
             (this.referenceNo!=null &&
              this.referenceNo.equals(other.getReferenceNo()))) &&
            ((this.fieldName==null && other.getFieldName()==null) || 
             (this.fieldName!=null &&
              this.fieldName.equals(other.getFieldName()))) &&
            ((this.newFieldValue==null && other.getNewFieldValue()==null) || 
             (this.newFieldValue!=null &&
              this.newFieldValue.equals(other.getNewFieldValue())));
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
        if (getAuthKey() != null) {
            _hashCode += getAuthKey().hashCode();
        }
        if (getReferenceNo() != null) {
            _hashCode += getReferenceNo().hashCode();
        }
        if (getFieldName() != null) {
            _hashCode += getFieldName().hashCode();
        }
        if (getNewFieldValue() != null) {
            _hashCode += getNewFieldValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateRequestCustomField.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">UpdateRequestCustomField"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "referenceNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "fieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newFieldValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "newFieldValue"));
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
