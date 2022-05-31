export interface Order {
    id: number,
    code:string,
    total:number,
    status:string,
    date:string
}
export const orders:Order[]=[
    {id: 0,code:"TQS1O1",status:"Delivered",date:"21/04/2022",total:12.13},
    {id: 1,code:"TQS1O2",status:"Delivered",date:"10/05/2022",total:1354.13},
    {id: 2,code:"TQS1O3",status:"Delivered",date:"21/05/2022",total:513.36},
    {id: 3,code:"TQS1O4",status:"In route",date:"24/05/2022",total:51.23},
    {id: 4,code:"TQS1O4",status:"In route",date:"25/05/2022",total:165.23},
    {id: 5,code:"TQS1O5",status:"In route",date:"26/05/2022",total:19.95},
    {id: 6,code:"TQS1O6",status:"In Route",date:"26/05/2022",total:420.63},
  ];
