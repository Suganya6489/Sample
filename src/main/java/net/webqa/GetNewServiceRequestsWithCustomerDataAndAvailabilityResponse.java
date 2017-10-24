/**
 * GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse  implements java.io.Serializable {
    private net.webqa.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponseGetNewServiceRequestsWithCustomerDataAndAvailabilityResult getNewServiceRequestsWithCustomerDataAndAvailabilityResult;

    public GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse() {
    }

    public GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse(
           net.webqa.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponseGetNewServiceRequestsWithCustomerDataAndAvailabilityResult getNewServiceRequestsWithCustomerDataAndAvailabilityResult) {
           this.getNewServiceRequestsWithCustomerDataAndAvailabilityResult = getNewServiceRequestsWithCustomerDataAndAvailabilityResult;
    }


    /**
     * Gets the getNewServiceRequestsWithCustomerDataAndAvailabilityResult value for this GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse.
     * 
     * @return getNewServiceRequestsWithCustomerDataAndAvailabilityResult
     */
    public net.webqa.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponseGetNewServiceRequestsWithCustomerDataAndAvailabilityResult getGetNewServiceRequestsWithCustomerDataAndAvailabilityResult() {
        return getNewServiceRequestsWithCustomerDataAndAvailabilityResult;
    }


    /**
     * Sets the getNewServiceRequestsWithCustomerDataAndAvailabilityResult value for this GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse.
     * 
     * @param getNewServiceRequestsWithCustomerDataAndAvailabilityResult
     */
    public void setGetNewServiceRequestsWithCustomerDataAndAvailabilityResult(net.webqa.GetNewServiceRequestsWithCustomerDataAndAvailabilityResponseGetNewServiceRequestsWithCustomerDataAndAvailabilityResult getNewServiceRequestsWithCustomerDataAndAvailabilityResult) {
        this.getNewServiceRequestsWithCustomerDataAndAvailabilityResult = getNewServiceRequestsWithCustomerDataAndAvailabilityResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse)) return false;
        GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse other = (GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNewServiceRequestsWithCustomerDataAndAvailabilityResult==null && other.getGetNewServiceRequestsWithCustomerDataAndAvailabilityResult()==null) || 
             (this.getNewServiceRequestsWithCustomerDataAndAvailabilityResult!=null &&
              this.getNewServiceRequestsWithCustomerDataAndAvailabilityResult.equals(other.getGetNewServiceRequestsWithCustomerDataAndAvailabilityResult())));
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
        if (getGetNewServiceRequestsWithCustomerDataAndAvailabilityResult() != null) {
            _hashCode += getGetNewServiceRequestsWithCustomerDataAndAvailabilityResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNewServiceRequestsWithCustomerDataAndAvailabilityResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetNewServiceRequestsWithCustomerDataAndAvailabilityResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetNewServiceRequestsWithCustomerDataAndAvailabilityResponse>GetNewServiceRequestsWithCustomerDataAndAvailabilityResult"));
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
