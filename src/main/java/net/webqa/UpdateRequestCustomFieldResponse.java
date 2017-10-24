/**
 * UpdateRequestCustomFieldResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class UpdateRequestCustomFieldResponse  implements java.io.Serializable {
    private boolean updateRequestCustomFieldResult;

    public UpdateRequestCustomFieldResponse() {
    }

    public UpdateRequestCustomFieldResponse(
           boolean updateRequestCustomFieldResult) {
           this.updateRequestCustomFieldResult = updateRequestCustomFieldResult;
    }


    /**
     * Gets the updateRequestCustomFieldResult value for this UpdateRequestCustomFieldResponse.
     * 
     * @return updateRequestCustomFieldResult
     */
    public boolean isUpdateRequestCustomFieldResult() {
        return updateRequestCustomFieldResult;
    }


    /**
     * Sets the updateRequestCustomFieldResult value for this UpdateRequestCustomFieldResponse.
     * 
     * @param updateRequestCustomFieldResult
     */
    public void setUpdateRequestCustomFieldResult(boolean updateRequestCustomFieldResult) {
        this.updateRequestCustomFieldResult = updateRequestCustomFieldResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateRequestCustomFieldResponse)) return false;
        UpdateRequestCustomFieldResponse other = (UpdateRequestCustomFieldResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.updateRequestCustomFieldResult == other.isUpdateRequestCustomFieldResult();
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
        _hashCode += (isUpdateRequestCustomFieldResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateRequestCustomFieldResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">UpdateRequestCustomFieldResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateRequestCustomFieldResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "UpdateRequestCustomFieldResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
