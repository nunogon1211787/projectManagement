import { useState, useContext, useEffect } from "react";
import { fetchAddToCollections, navToTable, fetchCollections } from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";
import Button from "./Button";


const initialFormData = {};

export default function ProjectForm(props) {

    const { state, dispatch } = useContext(AppContext);
    const { collection } = state;
    const { data } = collection;
    const [formData, setFormData] = useState(initialFormData);

    function makePostRequest() {
        const url = `${URL_API}/${props.collections}`;

        const postRequest = {
            method: "POST",
            headers: {
                "content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetchAddToCollections(url, postRequest, dispatch);
    }

    const buttonNavigate = () => {
        navToTable(dispatch);
    };

    useEffect(() => {
        let url2 = `${URL_API}/typologies`;
        const request = {};

        fetchCollections(url2, request, dispatch);
    }, []);


    function inputs() {

        return (
            <><form>
                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Project Name
                </label>

                <input
                    className="form-control"
                    type="text"
                    name="projectName"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Project Description
                </label>

                <textarea
                    className="form-control"
                    type="text"
                    name="description"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Business Sector
                </label>

                <input
                    className="form-control"
                    type="text"
                    name="businessSector"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Start Date
                </label>

                <input
                    className="form-control"
                    type="date"
                    name="startDate"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    End Date
                </label>

                <input
                    className="form-control"
                    type="date"
                    name="endDate"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label for="cars" >Select a typology:</label>
                <br></br>

               <select
                    className="form-control"
                    id="typology" name="typology"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value})
                    }>
                    <option disabled selected value> -- select an option -- </option>            
                    {Object.values(data[0]._embedded)[0].map(typo => {
                        return <option className="form-control" value={Object.values(typo)[0]}>{Object.values(typo)[0]}</option>
                    })}
                </select>

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Customer
                </label>

                <input
                    className="form-control"
                    type="text"
                    name="customer"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Number Of Sprints
                </label>

                <input
                    className="form-control"
                    type="number"
                    name="numberOfSprints"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Budget
                </label>

                <input
                    className="form-control"
                    placeholder="€"
                    type="number"
                    name="budget"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

                <label
                    className="form-label"
                    style={{
                        display: "inline-block",
                        textTransform: "capitalize",
                    }}
                >
                    Sprint Duration
                </label>

                <input
                    className="form-control"
                    type="number"
                    name="sprintDuration"
                    onChange={(e) =>
                        setFormData({ ...formData, [e.target.name]: e.target.value })
                    }
                />

            </form>
            </>
        );
    }

    return (
        <div className="card bg-light">
            <form className="card-body" onSubmit={(e) => {
                e.preventDefault()
                makePostRequest()
                console.log(formData)
            }}>
                {inputs()}
                <br />
                <Button name="create" type="submit" />
                <Button name="return" function={buttonNavigate} />
            </form>
        </div>
    );
}
