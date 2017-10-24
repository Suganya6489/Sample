/**
 * UpdateCustomerCustomFieldbyEmail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class UpdateCustomerCustomFieldbyEmail  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String customerEmail;

    private java.lang.String customFieldName;

    private java.lang.String customFieldValue;

    public UpdateCustomerCustomFieldbyEmail() {
    }

    public UpdateCustomerCustomFieldbyEmail(
           java.lang.String authKey,
           java.lang.String customerEmail,
           java.lang.String customFieldName,
           java.lang.String customFieldValue) {
           this.authKey = authKey;
           this.customerEmail = customerEmail;
           this.customFieldName = customFieldName;
           this.customFieldValue = customFieldValue;
    }


    /**
     * Gets the authKey value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the customerEmail value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @return customerEmail
     */
    public java.lang.String getCustomerEmail() {
        return customerEmail;
    }


    /**
     * Sets the customerEmail value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @param customerEmail
     */
    public void setCustomerEmail(java.lang.String customerEmail) {
        this.customerEmail = customerEmail;
    }


    /**
     * Gets the customFieldName value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @return customFieldName
     */
    public java.lang.String getCustomFieldName() {
        return customFieldName;
    }


    /**
     * Sets the customFieldName value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @param customFieldName
     */
    public void setCustomFieldName(java.lang.String customFieldName) {
        this.customFieldName = customFieldName;
    }


    /**
     * Gets the customFieldValue value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @return customFieldValue
     */
    public java.lang.String getCustomFieldValue() {
        return customFieldValue;
    }


    /**
     * Sets the customFieldValue value for this UpdateCustomerCustomFieldbyEmail.
     * 
     * @param customFieldValue
     */
    public void setCustomFieldValue(java.lang.String customFieldValue) {
        this.customFieldValue = customFieldValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomerCustomFieldbyEmail)) return false;
        UpdateCustomerCustomFieldbyEmail other = (UpdateCustomerCustomFieldbyEmail) obj;
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
            ((this.customerEmail==null && other.getCustomerEmail()==null) || 
             (this.customerEmail!=null &&
              this.customerEmail.equals(other.getCustomerEmail()))) &&
            ((this.customFieldName==null && other.getCustomFieldName()==null) || 
             (this.customFieldName!=null &&
              this.customFieldName.equals(other.getCustomFieldName()))) &&
            ((this.customFieldValue==null && other.getCustomFieldValue()==null) || 
             (this.customFieldValue!=null &&
              this.customFieldValue.equals(other.getCustomFieldValue())));
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
        if (getCustomerEmail() != null) {
            _hashCode += getCustomerEmail().hashCode();
        }
        if (getCustomFieldName() != null) {
            _hashCode += getCustomFieldName().hashCode();
        }
        if (getCustomFieldValue() != null) {
            _hashCode += getCustomFieldValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateCustomerCustomFieldbyEmail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">UpdateCustomerCustomFieldbyEmail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "customerEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "customFieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "customFieldValue"));
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
