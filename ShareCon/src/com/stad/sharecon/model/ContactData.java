package com.stad.sharecon.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.stad.sharecon.fragment.Constant;

public class ContactData {

	private ArrayList<String[]> tripleInputField = null;
	private String phoneticName = null;
	private String nickName = null;
	private String notes = null;
	private String internetCall = null;
	private String website = null;
	private String[] completeName = null;

	private final int PREFIX = 0;
	private final int FIRST = 1;
	private final int MIDDLE = 2;
	private final int LAST = 3;
	private final int SUFFIX = 4;

	public static final int TYPE = 0;
	public static final int SUB_TYPE = 1;
	public static final int INPUT = 2;

	public ContactData() {
		this.tripleInputField = new ArrayList<String[]>();
		this.completeName = new String[5];
		// this.email = new ArrayList<String[]>();
	}

	public static ContactData makeInstance(String json) {
		ContactData data = new ContactData();
		data.parseJSON(json);
		return data;
	}

	public void setName(String prefix, String first, String middle,
			String last, String suffix) {
		if (prefix == null)
			prefix = "";
		if (first == null)
			first = "";
		if (middle == null)
			middle = "";
		if (last == null)
			last = "";
		if (suffix == null)
			suffix = "";

		this.completeName[PREFIX] = prefix;
		this.completeName[FIRST] = first;
		this.completeName[MIDDLE] = middle;
		this.completeName[LAST] = last;
		this.completeName[SUFFIX] = suffix;
	}

	public String getName() {
		if (this.completeName[SUFFIX].length() > 0) {
			String name = this.completeName[PREFIX] + " "
					+ this.completeName[FIRST] + " "
					+ this.completeName[MIDDLE] + " " + this.completeName[LAST]
					+ ", " + this.completeName[SUFFIX];
			if (name.length() > 0)
				return name;
		} else {
			String name = this.completeName[PREFIX] + " "
					+ this.completeName[FIRST] + " "
					+ this.completeName[MIDDLE] + " " + this.completeName[LAST];
			if (name.length() > 0)
				return name;
		}
		return null;
	}

	public String getPrefix() {
		return this.completeName[PREFIX];
	}

	public String getFirst() {
		return this.completeName[FIRST];
	}

	public String getMiddle() {
		return this.completeName[MIDDLE];
	}

	public String getLast() {
		return this.completeName[LAST];
	}

	public String getSuffix() {
		return this.completeName[SUFFIX];
	}

	public void setInputField(String type, String subType, String input) {
		if (input.length() > 0) {
			String[] inputField = { type, subType, input };
			this.tripleInputField.add(inputField);
		}
	}

	public String[] getInputField(int index) {
		if (index < this.tripleInputField.size()) {
			return tripleInputField.get(index);
		}
		return null;
	}

	public void removeInputField(String inputField) {
		for (int i = 0; i < this.tripleInputField.size(); i++) {
			if (this.tripleInputField.get(i)[2].equals(inputField)) {
				this.tripleInputField.remove(i);
			}
		}
	}

	public void setPhoneticName(String phoneticName) {
		if (phoneticName.length() > 0)
			this.phoneticName = phoneticName;
	}

	public String getPhoneticname() {
		return this.phoneticName;
	}

	public void removePhoneticName() {
		this.phoneticName = null;
	}

	public void setnickName(String nickName) {
		if (nickName.length() > 0)
			this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void removeNickName() {
		this.nickName = null;
	}

	public void setNotes(String notes) {
		if (notes.length() > 0)
			this.notes = notes;
	}

	public String getNotes() {
		return this.notes;
	}

	public void removeNotes() {
		this.notes = null;
	}

	public void setInternetCall(String internetCall) {
		if (internetCall.length() > 0)
			this.internetCall = internetCall;
	}

	public String getInternetCall() {
		return this.internetCall;
	}

	public void removeInternetCall() {
		this.internetCall = null;
	}

	public void setWebsite(String website) {
		if (website.length() > 0)
			this.website = website;
	}

	public String getWebsite() {
		return this.website;
	}

	public void removeWebsite() {
		this.website = null;
	}

	public int getCount(String type) {
		int count = 0;
		for (int i = 0; i < this.tripleInputField.size(); i++) {
			if (this.tripleInputField.get(i)[TYPE].equals(type)) {
				count++;
			}
		}
		return count;
	}

	public int getTotalCount() {
		return this.tripleInputField.size();
	}

	public void printLog() {
		Log.i("ContactData", "Prefix:  " + this.completeName[PREFIX]);
		Log.i("ContactData", "First:  " + this.completeName[FIRST]);
		Log.i("ContactData", "Middle:  " + this.completeName[MIDDLE]);
		Log.i("ContactData", "Last:  " + this.completeName[LAST]);
		Log.i("ContactData", "Suffix:  " + this.completeName[SUFFIX]);
		Log.i("ContactData",
				"tripleInputFile size: " + this.tripleInputField.size());
		for (int i = 0; i < this.tripleInputField.size(); i++) {
			Log.i("ContactData", this.tripleInputField.get(i)[0] + ":   "
					+ this.tripleInputField.get(i)[1] + ",   "
					+ this.tripleInputField.get(i)[2]);
		}
		Log.i("ContactData", "PhoneticName:  " + this.phoneticName);
		Log.i("ContactData", "Nickname:  " + this.nickName);
		Log.i("ContactData", "Notes:  " + this.notes);
		Log.i("ContactData", "InternetCall:  " + this.internetCall);
		Log.i("ContactData", "Website:  " + this.website);
	}

	public JSONObject makeJSON() {
		JSONObject data = new JSONObject();
		JSONObject completeName = new JSONObject();
		try {
			completeName.put("prefix", this.completeName[PREFIX]);
			completeName.put("first", this.completeName[FIRST]);
			completeName.put("middle", this.completeName[MIDDLE]);
			completeName.put("last", this.completeName[LAST]);
			completeName.put("suffix", this.completeName[SUFFIX]);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONArray phone = new JSONArray();
		JSONArray email = new JSONArray();
		JSONArray address = new JSONArray();
		JSONArray im = new JSONArray();
		for (int i = 0; i < tripleInputField.size(); i++) {
			JSONObject field = new JSONObject();
			String[] fieldString = tripleInputField.get(i);
			try {
				field.put("sub_type", fieldString[SUB_TYPE]);
				field.put("input", fieldString[INPUT]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (fieldString[TYPE].equals(Constant.phone)) {
				phone.put(field);
			} else if (fieldString[TYPE].equals(Constant.email)) {
				email.put(field);
			} else if (fieldString[TYPE].equals(Constant.address)) {
				address.put(field);
			} else if (fieldString[TYPE].equals(Constant.im)) {
				im.put(field);
			}
		}

		try {
			data.put("name", completeName);
			data.put(Constant.phone, phone);
			data.put(Constant.email, email);
			data.put(Constant.address, address);
			data.put(Constant.im, im);
			data.put(Constant.website, this.website);
			data.put(Constant.phonetic_name, this.phoneticName);
			data.put(Constant.notes, this.notes);
			data.put(Constant.nickname, this.nickName);
			data.put(Constant.internet_call, this.internetCall);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void parseJSON(String json) {
		if (json != null) {
			JSONObject data = null;
			try {
				data = new JSONObject(json);

				this.website = getValue(data, Constant.website);
				this.phoneticName = getValue(data, Constant.phonetic_name);
				this.notes = getValue(data, Constant.notes);
				this.nickName = getValue(data, Constant.nickname);
				this.internetCall = getValue(data, Constant.internet_call);

				JSONObject completeName = data.getJSONObject("name");
				setName(getValue(completeName, "prefix"),
						getValue(completeName, "first"),
						getValue(completeName, "middle"),
						getValue(completeName, "last"),
						getValue(completeName, "suffix"));

				if (data.has(Constant.phone)) {
					Log.i("ContactData", "Parsing Phone");
					JSONArray phone = data.getJSONArray(Constant.phone);
					for (int i = 0; i < phone.length(); i++) {
						setInputField(Constant.phone, phone.getJSONObject(i)
								.getString("sub_type"), phone.getJSONObject(i)
								.getString("input"));
					}
				}
				if (data.has(Constant.email)) {
					Log.i("ContactData", "Parsing Email");
					JSONArray phone = data.getJSONArray(Constant.email);
					for (int i = 0; i < phone.length(); i++) {
						setInputField(Constant.email, phone.getJSONObject(i)
								.getString("sub_type"), phone.getJSONObject(i)
								.getString("input"));
					}
				}
				if (data.has(Constant.address)) {
					Log.i("ContactData", "Parsing Address");
					JSONArray phone = data.getJSONArray(Constant.address);
					for (int i = 0; i < phone.length(); i++) {
						setInputField(Constant.address, phone.getJSONObject(i)
								.getString("sub_type"), phone.getJSONObject(i)
								.getString("input"));
					}
				}
				if (data.has(Constant.im)) {
					Log.i("ContactData", "Parsing Im");
					JSONArray phone = data.getJSONArray(Constant.im);
					for (int i = 0; i < phone.length(); i++) {
						setInputField(Constant.im, phone.getJSONObject(i)
								.getString("sub_type"), phone.getJSONObject(i)
								.getString("input"));
					}
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

	private String getValue(JSONObject obj, String key) throws JSONException {
		if (obj.has(key)) {
			return obj.getString(key);
		}
		return null;
	}

}
