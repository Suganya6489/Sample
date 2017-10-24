/**
 * GetNewServiceRequestsWithCustomerDataAttachCount.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceRequestsWithCustomerDataAttachCount  implements java.io.Serializable {
    private java.lang.String authKey;

    private java.lang.String createDate;

    private boolean nonPrivateAttachmentsOnly;

    private java.lang.String filterExpression;

    public GetNewServiceRequestsWithCustomerDataAttachCount() {
    }

    public GetNewServiceRequestsWithCustomerDataAttachCount(
           java.lang.String authKey,
           java.lang.String createDate,
           boolean nonPrivateAttachmentsOnly,
           java.lang.String filterExpression) {
           this.authKey = authKey;
           this.createDate = createDate;
           this.nonPrivateAttachmentsOnly = nonPrivateAttachmentsOnly;
           this.filterExpression = filterExpression;
    }


    /**
     * Gets the authKey value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the createDate value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @return createDate
     */
    public java.lang.String getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @param createDate
     */
    public void setCreateDate(java.lang.String createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the nonPrivateAttachmentsOnly value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @return nonPrivateAttachmentsOnly
     */
    public boolean isNonPrivateAttachmentsOnly() {
        return nonPrivateAttachmentsOnly;
    }


    /**
     * Sets the nonPrivateAttachmentsOnly value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @param nonPrivateAttachmentsOnly
     */
    public void setNonPrivateAttachmentsOnly(boolean nonPrivateAttachmentsOnly) {
        this.nonPrivateAttachmentsOnly = nonPrivateAttachmentsOnly;
    }


    /**
     * Gets the filterExpression value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @return filterExpression
     */
    public java.lang.String getFilterExpression() {
        return filterExpression;
    }


    /**
     * Sets the filterExpression value for this GetNewServiceRequestsWithCustomerDataAttachCount.
     * 
     * @param filterExpression
     */
    public void setFilterExpression(java.lang.String filterExpression) {
        this.filterExpression = filterExpression;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceRequestsWithCustomerDataAttachCount)) return false;
        GetNewServiceRequestsWithCustomerDataAttachCount other = (GetNewServiceRequestsWithCustomerDataAttachCount) obj;
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
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            this.nonPrivateAttachmentsOnly == other.isNonPrivateAttachmentsOnly() &&
            ((this.filterExpression==null && other.getFilterExpression()==null) || 
             (this.filterExpression!=null &&
              this.filterExpression.equals(other.getFilterExpression())));
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
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        _hashCode += (isNonPrivateAttachmentsOnly() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFilterExpression() != null) {
            _hashCode += getFilterExpression().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceRequestsWithCustomerDataAttachCount.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceRequestsWithCustomerDataAttachCount"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "createDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nonPrivateAttachmentsOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "nonPrivateAttachmentsOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filterExpression");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "filterExpression"));
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
