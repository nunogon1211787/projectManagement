import React, { useEffect, useState } from 'react';

const SaveProject = () => {


    const [projectName, setProjectName] = useState("");
    const [description, setDescription] = useState("");
    const [bs, setBs] = useState("");
    const [startDate, setStartDate] = useState("");
    const [numberOfSprints, setNumberOfSprints] = useState("");
    const [sprintDuration, setSprintDuration] = useState("");
    const [budget, setBudget] = useState("");

    const [loading, setLoading] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault()
        const val = {projectName,description,bs,startDate,numberOfSprints,sprintDuration,budget}

        setLoading(true)

        fetch('http://localhost:8080/projects', {
            method: 'POST',
            headers: {"Content-Type" : "application/json" },
            body: JSON.stringify(val)
        }).then(() => {
            console.log('whatever')
            setLoading(false)
        })
    }
    return (

        <form onSubmit={handleSubmit}>
            <label htmlFor="projectName">Project Name</label>
            <input
                type="text"
                name="projectName"
                id="projectName"
                value={projectName}
                onChange={(e) => setProjectName(e.target.value)} />

            <label htmlFor="description">Description</label>
            <input
                type="text"
                name="description"
                id="description"
                value={description}
                onChange={(e) => setDescription(e.target.value)} />

            <label htmlFor="businessSector">Business Sector</label>
            <input
                type="text"
                name="businessSector"
                id="businessSector"
                value={bs}
                onChange={(e) => setBs(e.target.value)}/>

            <label htmlFor="startDate">Start Date</label>
            <input
                type="date"
                name="startDate"
                id="startDate"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}/>

            <label htmlFor="numberOfSprints">Number Of Sprints</label>
            <input
                type="number"
                name="numberOfSprints"
                id="numberOfSprints"
                value={numberOfSprints}
                onChange={(e) => setNumberOfSprints(e.target.value)}/>

            <label htmlFor="sprintDuration">Sprint Duration</label>
            <input
                type="number"
                name="sprintDuration"
                id="sprintDuration"
                value={sprintDuration}
                onChange={(e) => setSprintDuration(e.target.value)}/>

            <label htmlFor="budget">Budget</label>
            <input
                type="number"
                name="budget"
                id="budget"
                value={budget}
                onChange={(e) => setBudget(e.target.value)}/>

            {!loading && <button onClick={handleSubmit}> Save Project </button>}

            {loading && <button > Saving </button>}

            <p>{projectName}</p>
            <p>{startDate}</p>

        </form>
    );
};

export default SaveProject;