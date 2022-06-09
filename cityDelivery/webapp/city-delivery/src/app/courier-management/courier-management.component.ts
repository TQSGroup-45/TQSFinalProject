import { Component, OnInit, ViewChild } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import { fromLonLat } from 'ol/proj.js';
import {Tile as TileLayer, Vector as VectorLayer} from 'ol/layer';
import VectorSource from 'ol/source/Vector';
import {Icon, Style} from 'ol/style';
import OSM from 'ol/source/OSM';
import { Geometry } from 'ol/geom';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-courier-management',
  templateUrl: './courier-management.component.html',
  styleUrls: ['./courier-management.component.css']
})

export class CourierManagementComponent implements OnInit {

  map: Map | undefined;
  some: Feature | undefined;
  some2: Feature | undefined;
  some3: Feature | undefined;
  some4: Feature | undefined;
  vectorSource: VectorSource | undefined;
  vectorLayer: VectorLayer<VectorSource<Geometry>> | undefined;
  closeResult: string | undefined;
  displayedColumns: string[] = ['name', 'contact', 'profile', 'answer'];

  couriersList = new MatTableDataSource<object>( [
    {id:1,name: "José António", contact: 933917501,rating:4.0},
    {id:2,name: "Manuel Oliveira", contact: 933569756,rating:3.7},
    {id:3,name: "Josué Bento", contact: 917484541,rating:3.8},
    {id:4,name: "Arsenio Oliveira", contact: 918161232,rating:4.3},
    {id:5,name: "Alexandrina Aguiar", contact: 934277994,rating:3.8}

  ]);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.couriersList.paginator = this.paginator;
  }
  constructor(
    public dialog: MatDialog,
    ) {}



  ngOnInit(): void {
    this.some = new Feature({
      geometry: new Point(fromLonLat([-8.659163,40.633109]))
    });

    this.some.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [55,55 ]
      }))
    }))

    this.some2 = new Feature({
      geometry: new Point(fromLonLat([-8.652402,40.631077]))
    });

    this.some2.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [55,55 ]
      }))
    }))

    this.some3 = new Feature({
      geometry: new Point(fromLonLat([-8.656577,40.634268]))
    });

    this.some3.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [55,55 ]
      }))
    }))

    this.some4 = new Feature({
      geometry: new Point(fromLonLat([-8.650600,40.634692]))
    });

    this.some4.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [55,55 ]
      }))
    }))

    this.vectorSource = new VectorSource({
      features: [this.some,this.some2,this.some3,this.some4]
    });

    this.vectorLayer = new VectorLayer({
      source: this.vectorSource
    });

    this.map = new Map({
        target: 'ol-map',
        layers: [ new TileLayer({
          source: new OSM()
        }), this.vectorLayer ],
        view: new View({
          center: fromLonLat([-8.659163,40.633109]),
          zoom: 17
        })
      });

  }


}
