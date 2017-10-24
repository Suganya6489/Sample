/**
 * GetServiceRequestTypesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestTypesResponse  implements java.io.Serializable {
    private net.webqa.GetServiceRequestTypesResponseGetServiceRequestTypesResult getServiceRequestTypesResult;

    public GetServiceRequestTypesResponse() {
    }

    public GetServiceRequestTypesResponse(
           net.webqa.GetServiceRequestTypesResponseGetServiceRequestTypesResult getServiceRequestTypesResult) {
           this.getServiceRequestTypesResult = getServiceRequestTypesResult;
    }


    /**
     * Gets the getServiceRequestTypesResult value for this GetServiceRequestTypesResponse.
     * 
     * @return getServiceRequestTypesResult
     */
    public net.webqa.GetServiceRequestTypesResponseGetServiceRequestTypesResult getGetServiceRequestTypesResult() {
        return getServiceRequestTypesResult;
    }


    /**
     * Sets the getServiceRequestTypesResult value for this GetServiceRequestTypesResponse.
     * 
     * @param getServiceRequestTypesResult
     */
    public void setGetServiceRequestTypesResult(net.webqa.GetServiceRequestTypesResponseGetServiceRequestTypesResult getServiceRequestTypesResult) {
        this.getServiceRequestTypesResult = getServiceRequestTypesResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestTypesResponse)) return false;
        GetServiceRequestTypesResponse other = (GetServiceRequestTypesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestTypesResult==null && other.getGetServiceRequestTypesResult()==null) || 
             (this.getServiceRequestTypesResult!=null &&
              this.getServiceRequestTypesResult.equals(other.getGetServiceRequestTypesResult())));
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
        if (getGetServiceRequestTypesResult() != null) {
            _hashCode += getGetServiceRequestTypesResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestTypesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestTypesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestTypesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestTypesResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetServiceRequestTypesResponse>GetServiceRequestTypesResult"));
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
