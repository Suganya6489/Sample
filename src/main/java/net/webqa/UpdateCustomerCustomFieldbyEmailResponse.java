/**
 * UpdateCustomerCustomFieldbyEmailResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class UpdateCustomerCustomFieldbyEmailResponse  implements java.io.Serializable {
    private java.lang.String updateCustomerCustomFieldbyEmailResult;

    public UpdateCustomerCustomFieldbyEmailResponse() {
    }

    public UpdateCustomerCustomFieldbyEmailResponse(
           java.lang.String updateCustomerCustomFieldbyEmailResult) {
           this.updateCustomerCustomFieldbyEmailResult = updateCustomerCustomFieldbyEmailResult;
    }


    /**
     * Gets the updateCustomerCustomFieldbyEmailResult value for this UpdateCustomerCustomFieldbyEmailResponse.
     * 
     * @return updateCustomerCustomFieldbyEmailResult
     */
    public java.lang.String getUpdateCustomerCustomFieldbyEmailResult() {
        return updateCustomerCustomFieldbyEmailResult;
    }


    /**
     * Sets the updateCustomerCustomFieldbyEmailResult value for this UpdateCustomerCustomFieldbyEmailResponse.
     * 
     * @param updateCustomerCustomFieldbyEmailResult
     */
    public void setUpdateCustomerCustomFieldbyEmailResult(java.lang.String updateCustomerCustomFieldbyEmailResult) {
        this.updateCustomerCustomFieldbyEmailResult = updateCustomerCustomFieldbyEmailResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomerCustomFieldbyEmailResponse)) return false;
        UpdateCustomerCustomFieldbyEmailResponse other = (UpdateCustomerCustomFieldbyEmailResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.updateCustomerCustomFieldbyEmailResult==null && other.getUpdateCustomerCustomFieldbyEmailResult()==null) || 
             (this.updateCustomerCustomFieldbyEmailResult!=null &&
              this.updateCustomerCustomFieldbyEmailResult.equals(other.getUpdateCustomerCustomFieldbyEmailResult())));
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
        if (getUpdateCustomerCustomFieldbyEmailResult() != null) {
            _hashCode += getUpdateCustomerCustomFieldbyEmailResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateCustomerCustomFieldbyEmailResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">UpdateCustomerCustomFieldbyEmailResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateCustomerCustomFieldbyEmailResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "UpdateCustomerCustomFieldbyEmailResult"));
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
