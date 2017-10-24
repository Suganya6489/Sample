/**
 * WebQACustomer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

public class WebQACustomer  implements java.io.Serializable {
    private int id;

    private java.lang.String title;

    private java.lang.String first;

    private java.lang.String middle;

    private java.lang.String last;

    private java.lang.String phone;

    private java.lang.String addressOne;

    private java.lang.String addressTwo;

    private java.lang.String city;

    private java.lang.String state;

    private java.lang.String zip;

    private java.lang.String groupName;

    private java.lang.String email;
    
	private int srTypeId;
	
    private java.lang.String srType;
	
	private int customerId;
	
    private net.webqa.WebQACustomField[] customFieldData;
    
    private java.lang.String lAddress;

    public WebQACustomer() {
    }

    public WebQACustomer(
           int id,
           java.lang.String title,
           java.lang.String first,
           java.lang.String middle,
           java.lang.String last,
           java.lang.String phone,
           java.lang.String addressOne,
           java.lang.String addressTwo,
           java.lang.String city,
           java.lang.String state,
           java.lang.String zip,
           java.lang.String groupName,
           java.lang.String email,
           java.lang.String lAddress,
           net.webqa.WebQACustomField[] customFieldData) {
           this.id = id;
           this.title = title;
           this.first = first;
           this.middle = middle;
           this.last = last;
           this.phone = phone;
           this.addressOne = addressOne;
           this.addressTwo = addressTwo;
           this.city = city;
           this.state = state;
           this.zip = zip;
           this.groupName = groupName;
           this.email = email;
           this.customFieldData = customFieldData;
           this.lAddress = lAddress;
    }


    /**
     * Gets the id value for this WebQACustomer.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this WebQACustomer.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the title value for this WebQACustomer.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this WebQACustomer.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the first value for this WebQACustomer.
     * 
     * @return first
     */
    public java.lang.String getFirst() {
        return first;
    }


    /**
     * Sets the first value for this WebQACustomer.
     * 
     * @param first
     */
    public void setFirst(java.lang.String first) {
        this.first = first;
    }


    /**
     * Gets the middle value for this WebQACustomer.
     * 
     * @return middle
     */
    public java.lang.String getMiddle() {
        return middle;
    }


    /**
     * Sets the middle value for this WebQACustomer.
     * 
     * @param middle
     */
    public void setMiddle(java.lang.String middle) {
        this.middle = middle;
    }


    /**
     * Gets the last value for this WebQACustomer.
     * 
     * @return last
     */
    public java.lang.String getLast() {
        return last;
    }


    /**
     * Sets the last value for this WebQACustomer.
     * 
     * @param last
     */
    public void setLast(java.lang.String last) {
        this.last = last;
    }


    /**
     * Gets the phone value for this WebQACustomer.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this WebQACustomer.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the addressOne value for this WebQACustomer.
     * 
     * @return addressOne
     */
    public java.lang.String getAddressOne() {
        return addressOne;
    }


    /**
     * Sets the addressOne value for this WebQACustomer.
     * 
     * @param addressOne
     */
    public void setAddressOne(java.lang.String addressOne) {
        this.addressOne = addressOne;
    }


    /**
     * Gets the addressTwo value for this WebQACustomer.
     * 
     * @return addressTwo
     */
    public java.lang.String getAddressTwo() {
        return addressTwo;
    }


    /**
     * Sets the addressTwo value for this WebQACustomer.
     * 
     * @param addressTwo
     */
    public void setAddressTwo(java.lang.String addressTwo) {
        this.addressTwo = addressTwo;
    }


    /**
     * Gets the city value for this WebQACustomer.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this WebQACustomer.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the state value for this WebQACustomer.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this WebQACustomer.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the zip value for this WebQACustomer.
     * 
     * @return zip
     */
    public java.lang.String getZip() {
        return zip;
    }


    /**
     * Sets the zip value for this WebQACustomer.
     * 
     * @param zip
     */
    public void setZip(java.lang.String zip) {
        this.zip = zip;
    }


    /**
     * Gets the groupName value for this WebQACustomer.
     * 
     * @return groupName
     */
    public java.lang.String getGroupName() {
        return groupName;
    }


    /**
     * Sets the groupName value for this WebQACustomer.
     * 
     * @param groupName
     */
    public void setGroupName(java.lang.String groupName) {
        this.groupName = groupName;
    }


    /**
     * Gets the email value for this WebQACustomer.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this WebQACustomer.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the customFieldData value for this WebQACustomer.
     * 
     * @return customFieldData
     */
    public net.webqa.WebQACustomField[] getCustomFieldData() {
        return customFieldData;
    }


    /**
     * Sets the customFieldData value for this WebQACustomer.
     * 
     * @param customFieldData
     */
    public void setCustomFieldData(net.webqa.WebQACustomField[] customFieldData) {
        this.customFieldData = customFieldData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebQACustomer)) return false;
        WebQACustomer other = (WebQACustomer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.first==null && other.getFirst()==null) || 
             (this.first!=null &&
              this.first.equals(other.getFirst()))) &&
            ((this.middle==null && other.getMiddle()==null) || 
             (this.middle!=null &&
              this.middle.equals(other.getMiddle()))) &&
            ((this.last==null && other.getLast()==null) || 
             (this.last!=null &&
              this.last.equals(other.getLast()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.addressOne==null && other.getAddressOne()==null) || 
             (this.addressOne!=null &&
              this.addressOne.equals(other.getAddressOne()))) &&
            ((this.addressTwo==null && other.getAddressTwo()==null) || 
             (this.addressTwo!=null &&
              this.addressTwo.equals(other.getAddressTwo()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.zip==null && other.getZip()==null) || 
             (this.zip!=null &&
              this.zip.equals(other.getZip()))) &&
            ((this.groupName==null && other.getGroupName()==null) || 
             (this.groupName!=null &&
              this.groupName.equals(other.getGroupName()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.customFieldData==null && other.getCustomFieldData()==null) || 
             (this.customFieldData!=null &&
              java.util.Arrays.equals(this.customFieldData, other.getCustomFieldData())));
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
        _hashCode += getId();
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getFirst() != null) {
            _hashCode += getFirst().hashCode();
        }
        if (getMiddle() != null) {
            _hashCode += getMiddle().hashCode();
        }
        if (getLast() != null) {
            _hashCode += getLast().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getAddressOne() != null) {
            _hashCode += getAddressOne().hashCode();
        }
        if (getAddressTwo() != null) {
            _hashCode += getAddressTwo().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getZip() != null) {
            _hashCode += getZip().hashCode();
        }
        if (getGroupName() != null) {
            _hashCode += getGroupName().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getCustomFieldData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCustomFieldData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCustomFieldData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebQACustomer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "WebQACustomer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "first"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "middle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "last"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressOne");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "addressOne"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressTwo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "addressTwo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "zip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "groupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webqa.net/", "customFieldData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://webqa.net/", "WebQACustomField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://webqa.net/", "WebQACustomField"));
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

	/**
	 * @return the srTypeId
	 */
	public int getSrTypeId() {
		return srTypeId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param srTypeId the srTypeId to set
	 */
	public void setSrTypeId(int srTypeId) {
		this.srTypeId = srTypeId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the srType
	 */
	public java.lang.String getSrType() {
		return srType;
	}

	/**
	 * @param srType the srType to set
	 */
	public void setSrType(java.lang.String srType) {
		this.srType = srType;
	}

	/**
	 * @return the lAddress
	 */
	public java.lang.String getlAddress() {
		return lAddress;
	}

	/**
	 * @param lAddress the lAddress to set
	 */
	public void setlAddress(java.lang.String lAddress) {
		this.lAddress = lAddress;
	}

}
