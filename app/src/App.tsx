import React, { useState, useEffect } from "react";
import "./App.css";
import { Button } from "primereact/button";
import { Dropdown } from "primereact/dropdown";
import { Image } from "primereact/image";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

function App() {
  const [selectedFacility, setSelectedFacility] = useState(null);
  const [selectedCity, setSelectedCity] = useState(null);
  const [selectedExpertise, setSelectedExpertise] = useState(null);

  const [doctors, setDoctors] = useState([]);
  const [cities, setCities] = useState([]);
  const [facilities, setFacilities] = useState([]);

  const expertise = [
    "Allergy and Immunology",
    "Anesthesiology",
    "Colon and Rectal Surgery",
    "Dermatology",
    "Emergency Medicine",
  ];

  const onSearchClick = () =>
    fetch(
      `${process.env.REACT_APP_API_URL}/doctors?city=${encodeURIComponent(selectedCity ? selectedCity : '')}&facility=${encodeURIComponent(selectedFacility ? selectedFacility : '')}&expertise=${encodeURIComponent(selectedExpertise ? selectedExpertise : '')}`
    )
      .then((response) => response.json())
      .then((result) => setDoctors(result));

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_URL}/doctors/cities`)
      .then((reponse) => reponse.json())
      .then((result) => setCities(result));
  }, []);

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_URL}/doctors/facilities`)
      .then((reponse) => reponse.json())
      .then((result) => setFacilities(result));
  }, []);

  return (
    <div className="App">
      <header className="mb-3">
        <Image imageClassName="border-round shadow-2" src="/header2.jpg" width="100%" />
      </header>
      <div className="mb-3 p-5 border-round surface-50 shadow-2">
        <div className="grid">
          <div className="col-12 md:col-3 p-fluid">
            <Dropdown
              value={selectedCity}
              options={cities}
              onChange={(e) => setSelectedCity(e.value)}
              placeholder="Select a City"
              showClear
            />
          </div>
          <div className="col-12 md:col-3 p-fluid">
            <Dropdown
              value={selectedFacility}
              options={facilities}
              onChange={(e) => setSelectedFacility(e.value)}
              placeholder="Select a Facility"
              showClear
            />
          </div>
          <div className="col-12 md:col-3 p-fluid">
            <Dropdown
              value={selectedExpertise}
              options={expertise}
              onChange={(e) => setSelectedExpertise(e.value)}
              placeholder="Select a Expertise"
              showClear
            />
          </div>
          <div className="col-12 md:col-3 p-fluid">
            <Button label="Search" onClick={onSearchClick} />
          </div>
        </div>
      </div>
      <div className="border-round surface-100 shadow-2">
        <DataTable paginator value={doctors} rows={10} size="large">
          <Column field="id" header="ID"></Column>
          <Column field="firstName" header="Firstname"></Column>
          <Column field="lastName" header="Lastname"></Column>
          <Column field="email" header="E-Mail"></Column>
          <Column field="city" header="City"></Column>
          <Column field="areaOfExpertise" header="Area of Expertise"></Column>
          <Column field="facility" header="Facility"></Column>
        </DataTable>
      </div>
    </div>
  );
}

export default App;
