package com.example.tests;

public class ContactDataForPrintPhones implements Comparable<ContactDataForPrintPhones> {
		private String unitedName;
		private String home_tel;
		
		public ContactDataForPrintPhones(String unitedName,String home_tel) {
			this.unitedName = unitedName;
			this.home_tel=home_tel;
		}
		public ContactDataForPrintPhones(){
		
			}
		
		public String getUnitedName() {
			return unitedName;
		}
		public void setUnitedName(String unitedName) {
			this.unitedName = unitedName;
		}
		public String getHome_tel() {
			return home_tel;
		}
		public void setHome_tel(String home_tel) {
			this.home_tel = home_tel;
		}
			
		public ContactDataForPrintPhones withUnitedName(String unitedName) {
			this.unitedName = unitedName;
			return this;
		}
		public ContactDataForPrintPhones withHome_Tel(String home_tel) {
			this.home_tel = home_tel;
			return this;
		}			
		@Override
		public int compareTo(ContactDataForPrintPhones other) {
		return this.unitedName.toLowerCase().compareTo(other.unitedName.toLowerCase());
		
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((home_tel == null) ? 0 : home_tel.hashCode());
			result = prime * result + ((unitedName == null) ? 0 : unitedName.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ContactDataForPrintPhones other = (ContactDataForPrintPhones) obj;
			if (home_tel == null) {
				if (other.home_tel != null)
					return false;
			} else if (!home_tel.trim().equals(other.home_tel.trim()))
				return false;
			if (unitedName == null) {
				if (other.unitedName != null)
					return false;
			} else if (!unitedName.trim().equals(other.unitedName.trim()))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "ContactDataForPrintPhones [unitedName=" + unitedName + ", home_tel=" + home_tel + "]";
		}
	
}
