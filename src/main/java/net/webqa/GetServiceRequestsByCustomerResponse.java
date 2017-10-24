/**
 * GetServiceRequestsByCustomerResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestsByCustomerResponse  implements java.io.Serializable {
    private net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult getServiceRequestsByCustomerResult;

    public GetServiceRequestsByCustomerResponse() {
    }

    public GetServiceRequestsByCustomerResponse(
           net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult getServiceRequestsByCustomerResult) {
           this.getServiceRequestsByCustomerResult = getServiceRequestsByCustomerResult;
    }


    /**
     * Gets the getServiceRequestsByCustomerResult value for this GetServiceRequestsByCustomerResponse.
     * 
     * @return getServiceRequestsByCustomerResult
     */
    public net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult getGetServiceRequestsByCustomerResult() {
        return getServiceRequestsByCustomerResult;
    }


    /**
     * Sets the getServiceRequestsByCustomerResult value for this GetServiceRequestsByCustomerResponse.
     * 
     * @param getServiceRequestsByCustomerResult
     */
    public void setGetServiceRequestsByCustomerResult(net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult getServiceRequestsByCustomerResult) {
        this.getServiceRequestsByCustomerResult = getServiceRequestsByCustomerResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestsByCustomerResponse)) return false;
        GetServiceRequestsByCustomerResponse other = (GetServiceRequestsByCustomerResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestsByCustomerResult==null && other.getGetServiceRequestsByCustomerResult()==null) || 
             (this.getServiceRequestsByCustomerResult!=null &&
              this.getServiceRequestsByCustomerResult.equals(other.getGetServiceRequestsByCustomerResult())));
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
        if (getGetServiceRequestsByCustomerResult() != null) {
            _hashCode += getGetServiceRequestsByCustomerResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestsByCustomerResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestsByCustomerResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestsByCustomerResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestsByCustomerResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetServiceRequestsByCustomerResponse>GetServiceRequestsByCustomerResult"));
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
