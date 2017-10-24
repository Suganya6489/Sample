/**
 * UpdateRequestCustomFieldById.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class UpdateRequestCustomFieldById  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String referenceNo;

    private int fieldId;

    private java.lang.String newFieldValue;

    public UpdateRequestCustomFieldById() {
    }

    public UpdateRequestCustomFieldById(
           java.lang.String authKey,
           java.lang.String referenceNo,
           int fieldId,
           java.lang.String newFieldValue) {
           this.authKey = authKey;
           this.referenceNo = referenceNo;
           this.fieldId = fieldId;
           this.newFieldValue = newFieldValue;
    }


    /**
     * Gets the authKey value for this UpdateRequestCustomFieldById.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this UpdateRequestCustomFieldById.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the referenceNo value for this UpdateRequestCustomFieldById.
     * 
     * @return referenceNo
     */
    public java.lang.String getReferenceNo() {
        return referenceNo;
    }


    /**
     * Sets the referenceNo value for this UpdateRequestCustomFieldById.
     * 
     * @param referenceNo
     */
    public void setReferenceNo(java.lang.String referenceNo) {
        this.referenceNo = referenceNo;
    }


    /**
     * Gets the fieldId value for this UpdateRequestCustomFieldById.
     * 
     * @return fieldId
     */
    public int getFieldId() {
        return fieldId;
    }


    /**
     * Sets the fieldId value for this UpdateRequestCustomFieldById.
     * 
     * @param fieldId
     */
    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }


    /**
     * Gets the newFieldValue value for this UpdateRequestCustomFieldById.
     * 
     * @return newFieldValue
     */
    public java.lang.String getNewFieldValue() {
        return newFieldValue;
    }


    /**
     * Sets the newFieldValue value for this UpdateRequestCustomFieldById.
     * 
     * @param newFieldValue
     */
    public void setNewFieldValue(java.lang.String newFieldValue) {
        this.newFieldValue = newFieldValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateRequestCustomFieldById)) return false;
        UpdateRequestCustomFieldById other = (UpdateRequestCustomFieldById) obj;
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
            this.fieldId == other.getFieldId() &&
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
        _hashCode += getFieldId();
        if (getNewFieldValue() != null) {
            _hashCode += getNewFieldValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateRequestCustomFieldById.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">UpdateRequestCustomFieldById"));
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
        elemField.setFieldName("fieldId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "fieldId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
