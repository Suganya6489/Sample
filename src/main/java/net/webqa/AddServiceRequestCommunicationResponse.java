/**
 * AddServiceRequestCommunicationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class AddServiceRequestCommunicationResponse  implements java.io.Serializable {
    private java.lang.String addServiceRequestCommunicationResult;

    public AddServiceRequestCommunicationResponse() {
    }

    public AddServiceRequestCommunicationResponse(
           java.lang.String addServiceRequestCommunicationResult) {
           this.addServiceRequestCommunicationResult = addServiceRequestCommunicationResult;
    }


    /**
     * Gets the addServiceRequestCommunicationResult value for this AddServiceRequestCommunicationResponse.
     * 
     * @return addServiceRequestCommunicationResult
     */
    public java.lang.String getAddServiceRequestCommunicationResult() {
        return addServiceRequestCommunicationResult;
    }


    /**
     * Sets the addServiceRequestCommunicationResult value for this AddServiceRequestCommunicationResponse.
     * 
     * @param addServiceRequestCommunicationResult
     */
    public void setAddServiceRequestCommunicationResult(java.lang.String addServiceRequestCommunicationResult) {
        this.addServiceRequestCommunicationResult = addServiceRequestCommunicationResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddServiceRequestCommunicationResponse)) return false;
        AddServiceRequestCommunicationResponse other = (AddServiceRequestCommunicationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addServiceRequestCommunicationResult==null && other.getAddServiceRequestCommunicationResult()==null) || 
             (this.addServiceRequestCommunicationResult!=null &&
              this.addServiceRequestCommunicationResult.equals(other.getAddServiceRequestCommunicationResult())));
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
        if (getAddServiceRequestCommunicationResult() != null) {
            _hashCode += getAddServiceRequestCommunicationResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddServiceRequestCommunicationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">AddServiceRequestCommunicationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addServiceRequestCommunicationResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "AddServiceRequestCommunicationResult"));
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
