import React from 'react'
import './../App.css';
import {
    LineChart,
    ResponsiveContainer,
    Legend, Tooltip,
    Line,
    XAxis,
    YAxis,
    CartesianGrid
} from 'recharts';

function LineGraph({data}) {
    return (
        <>

            <ResponsiveContainer width="50%" aspect={3}>
                <LineChart data={data} margin={{ right: 300 }}>
                    <CartesianGrid />
                    <XAxis dataKey="date" 
                        interval={'preserveStartEnd'} />
                    <YAxis></YAxis>
                    <Legend />
                    <Tooltip />
                    <Line dataKey="unit" name='calories'
                        stroke="black" activeDot={{ r: 8 }} />
                    <Line dataKey="fees"
                        stroke="red" activeDot={{ r: 8 }} />
                </LineChart>
            </ResponsiveContainer>
        </>
    );
}

export default LineGraph;