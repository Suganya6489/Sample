/**
 * GetNewServiceReqeustsWIthPropertyDataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class GetNewServiceReqeustsWIthPropertyDataResponse  implements java.io.Serializable {
    private net.webqa.GetNewServiceReqeustsWIthPropertyDataResponseGetNewServiceReqeustsWIthPropertyDataResult getNewServiceReqeustsWIthPropertyDataResult;

    public GetNewServiceReqeustsWIthPropertyDataResponse() {
    }

    public GetNewServiceReqeustsWIthPropertyDataResponse(
           net.webqa.GetNewServiceReqeustsWIthPropertyDataResponseGetNewServiceReqeustsWIthPropertyDataResult getNewServiceReqeustsWIthPropertyDataResult) {
           this.getNewServiceReqeustsWIthPropertyDataResult = getNewServiceReqeustsWIthPropertyDataResult;
    }


    /**
     * Gets the getNewServiceReqeustsWIthPropertyDataResult value for this GetNewServiceReqeustsWIthPropertyDataResponse.
     * 
     * @return getNewServiceReqeustsWIthPropertyDataResult
     */
    public net.webqa.GetNewServiceReqeustsWIthPropertyDataResponseGetNewServiceReqeustsWIthPropertyDataResult getGetNewServiceReqeustsWIthPropertyDataResult() {
        return getNewServiceReqeustsWIthPropertyDataResult;
    }


    /**
     * Sets the getNewServiceReqeustsWIthPropertyDataResult value for this GetNewServiceReqeustsWIthPropertyDataResponse.
     * 
     * @param getNewServiceReqeustsWIthPropertyDataResult
     */
    public void setGetNewServiceReqeustsWIthPropertyDataResult(net.webqa.GetNewServiceReqeustsWIthPropertyDataResponseGetNewServiceReqeustsWIthPropertyDataResult getNewServiceReqeustsWIthPropertyDataResult) {
        this.getNewServiceReqeustsWIthPropertyDataResult = getNewServiceReqeustsWIthPropertyDataResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNewServiceReqeustsWIthPropertyDataResponse)) return false;
        GetNewServiceReqeustsWIthPropertyDataResponse other = (GetNewServiceReqeustsWIthPropertyDataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getNewServiceReqeustsWIthPropertyDataResult==null && other.getGetNewServiceReqeustsWIthPropertyDataResult()==null) || 
             (this.getNewServiceReqeustsWIthPropertyDataResult!=null &&
              this.getNewServiceReqeustsWIthPropertyDataResult.equals(other.getGetNewServiceReqeustsWIthPropertyDataResult())));
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
        if (getGetNewServiceReqeustsWIthPropertyDataResult() != null) {
            _hashCode += getGetNewServiceReqeustsWIthPropertyDataResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNewServiceReqeustsWIthPropertyDataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">GetNewServiceReqeustsWIthPropertyDataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getNewServiceReqeustsWIthPropertyDataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "GetNewServiceReqeustsWIthPropertyDataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", ">>GetNewServiceReqeustsWIthPropertyDataResponse>GetNewServiceReqeustsWIthPropertyDataResult"));
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
