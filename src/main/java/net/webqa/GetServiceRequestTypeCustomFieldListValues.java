/**
 * GetServiceRequestTypeCustomFieldListValues.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestTypeCustomFieldListValues  implements java.io.Serializable {
    private java.lang.String authKey;

    private int serviceRequestFieldId;

    public GetServiceRequestTypeCustomFieldListValues() {
    }

    public GetServiceRequestTypeCustomFieldListValues(
           java.lang.String authKey,
           int serviceRequestFieldId) {
           this.authKey = authKey;
           this.serviceRequestFieldId = serviceRequestFieldId;
    }


    /**
     * Gets the authKey value for this GetServiceRequestTypeCustomFieldListValues.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this GetServiceRequestTypeCustomFieldListValues.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the serviceRequestFieldId value for this GetServiceRequestTypeCustomFieldListValues.
     * 
     * @return serviceRequestFieldId
     */
    public int getServiceRequestFieldId() {
        return serviceRequestFieldId;
    }


    /**
     * Sets the serviceRequestFieldId value for this GetServiceRequestTypeCustomFieldListValues.
     * 
     * @param serviceRequestFieldId
     */
    public void setServiceRequestFieldId(int serviceRequestFieldId) {
        this.serviceRequestFieldId = serviceRequestFieldId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestTypeCustomFieldListValues)) return false;
        GetServiceRequestTypeCustomFieldListValues other = (GetServiceRequestTypeCustomFieldListValues) obj;
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
            this.serviceRequestFieldId == other.getServiceRequestFieldId();
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
        _hashCode += getServiceRequestFieldId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestTypeCustomFieldListValues.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestTypeCustomFieldListValues"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestFieldId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "serviceRequestFieldId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
