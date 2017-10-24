/**
 * GetServiceRequestMappingsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestMappingsResponse  implements java.io.Serializable {
    private java.lang.String getServiceRequestMappingsResult;

    public GetServiceRequestMappingsResponse() {
    }

    public GetServiceRequestMappingsResponse(
           java.lang.String getServiceRequestMappingsResult) {
           this.getServiceRequestMappingsResult = getServiceRequestMappingsResult;
    }


    /**
     * Gets the getServiceRequestMappingsResult value for this GetServiceRequestMappingsResponse.
     * 
     * @return getServiceRequestMappingsResult
     */
    public java.lang.String getGetServiceRequestMappingsResult() {
        return getServiceRequestMappingsResult;
    }


    /**
     * Sets the getServiceRequestMappingsResult value for this GetServiceRequestMappingsResponse.
     * 
     * @param getServiceRequestMappingsResult
     */
    public void setGetServiceRequestMappingsResult(java.lang.String getServiceRequestMappingsResult) {
        this.getServiceRequestMappingsResult = getServiceRequestMappingsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestMappingsResponse)) return false;
        GetServiceRequestMappingsResponse other = (GetServiceRequestMappingsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestMappingsResult==null && other.getGetServiceRequestMappingsResult()==null) || 
             (this.getServiceRequestMappingsResult!=null &&
              this.getServiceRequestMappingsResult.equals(other.getGetServiceRequestMappingsResult())));
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
        if (getGetServiceRequestMappingsResult() != null) {
            _hashCode += getGetServiceRequestMappingsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestMappingsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestMappingsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestMappingsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestMappingsResult"));
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
