/**
 * GetServiceRequestByReferenceNumberResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetServiceRequestByReferenceNumberResponse  implements java.io.Serializable {
    private net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult getServiceRequestByReferenceNumberResult;

    public GetServiceRequestByReferenceNumberResponse() {
    }

    public GetServiceRequestByReferenceNumberResponse(
           net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult getServiceRequestByReferenceNumberResult) {
           this.getServiceRequestByReferenceNumberResult = getServiceRequestByReferenceNumberResult;
    }


    /**
     * Gets the getServiceRequestByReferenceNumberResult value for this GetServiceRequestByReferenceNumberResponse.
     * 
     * @return getServiceRequestByReferenceNumberResult
     */
    public net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult getGetServiceRequestByReferenceNumberResult() {
        return getServiceRequestByReferenceNumberResult;
    }


    /**
     * Sets the getServiceRequestByReferenceNumberResult value for this GetServiceRequestByReferenceNumberResponse.
     * 
     * @param getServiceRequestByReferenceNumberResult
     */
    public void setGetServiceRequestByReferenceNumberResult(net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult getServiceRequestByReferenceNumberResult) {
        this.getServiceRequestByReferenceNumberResult = getServiceRequestByReferenceNumberResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestByReferenceNumberResponse)) return false;
        GetServiceRequestByReferenceNumberResponse other = (GetServiceRequestByReferenceNumberResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestByReferenceNumberResult==null && other.getGetServiceRequestByReferenceNumberResult()==null) || 
             (this.getServiceRequestByReferenceNumberResult!=null &&
              this.getServiceRequestByReferenceNumberResult.equals(other.getGetServiceRequestByReferenceNumberResult())));
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
        if (getGetServiceRequestByReferenceNumberResult() != null) {
            _hashCode += getGetServiceRequestByReferenceNumberResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestByReferenceNumberResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetServiceRequestByReferenceNumberResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestByReferenceNumberResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetServiceRequestByReferenceNumberResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetServiceRequestByReferenceNumberResponse>GetServiceRequestByReferenceNumberResult"));
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
