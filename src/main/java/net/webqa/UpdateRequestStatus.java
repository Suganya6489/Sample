/**
 * UpdateRequestStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class UpdateRequestStatus  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String referenceNo;

    private java.lang.String customFieldName;

    private java.lang.String customFieldValue;

    private java.lang.String newStatusValue;

    public UpdateRequestStatus() {
    }

    public UpdateRequestStatus(
           java.lang.String authKey,
           java.lang.String referenceNo,
           java.lang.String customFieldName,
           java.lang.String customFieldValue,
           java.lang.String newStatusValue) {
           this.authKey = authKey;
           this.referenceNo = referenceNo;
           this.customFieldName = customFieldName;
           this.customFieldValue = customFieldValue;
           this.newStatusValue = newStatusValue;
    }


    /**
     * Gets the authKey value for this UpdateRequestStatus.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this UpdateRequestStatus.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the referenceNo value for this UpdateRequestStatus.
     * 
     * @return referenceNo
     */
    public java.lang.String getReferenceNo() {
        return referenceNo;
    }


    /**
     * Sets the referenceNo value for this UpdateRequestStatus.
     * 
     * @param referenceNo
     */
    public void setReferenceNo(java.lang.String referenceNo) {
        this.referenceNo = referenceNo;
    }


    /**
     * Gets the customFieldName value for this UpdateRequestStatus.
     * 
     * @return customFieldName
     */
    public java.lang.String getCustomFieldName() {
        return customFieldName;
    }


    /**
     * Sets the customFieldName value for this UpdateRequestStatus.
     * 
     * @param customFieldName
     */
    public void setCustomFieldName(java.lang.String customFieldName) {
        this.customFieldName = customFieldName;
    }


    /**
     * Gets the customFieldValue value for this UpdateRequestStatus.
     * 
     * @return customFieldValue
     */
    public java.lang.String getCustomFieldValue() {
        return customFieldValue;
    }


    /**
     * Sets the customFieldValue value for this UpdateRequestStatus.
     * 
     * @param customFieldValue
     */
    public void setCustomFieldValue(java.lang.String customFieldValue) {
        this.customFieldValue = customFieldValue;
    }


    /**
     * Gets the newStatusValue value for this UpdateRequestStatus.
     * 
     * @return newStatusValue
     */
    public java.lang.String getNewStatusValue() {
        return newStatusValue;
    }


    /**
     * Sets the newStatusValue value for this UpdateRequestStatus.
     * 
     * @param newStatusValue
     */
    public void setNewStatusValue(java.lang.String newStatusValue) {
        this.newStatusValue = newStatusValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateRequestStatus)) return false;
        UpdateRequestStatus other = (UpdateRequestStatus) obj;
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
            ((this.customFieldName==null && other.getCustomFieldName()==null) || 
             (this.customFieldName!=null &&
              this.customFieldName.equals(other.getCustomFieldName()))) &&
            ((this.customFieldValue==null && other.getCustomFieldValue()==null) || 
             (this.customFieldValue!=null &&
              this.customFieldValue.equals(other.getCustomFieldValue()))) &&
            ((this.newStatusValue==null && other.getNewStatusValue()==null) || 
             (this.newStatusValue!=null &&
              this.newStatusValue.equals(other.getNewStatusValue())));
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
        if (getCustomFieldName() != null) {
            _hashCode += getCustomFieldName().hashCode();
        }
        if (getCustomFieldValue() != null) {
            _hashCode += getCustomFieldValue().hashCode();
        }
        if (getNewStatusValue() != null) {
            _hashCode += getNewStatusValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateRequestStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">UpdateRequestStatus"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newStatusValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "newStatusValue"));
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
