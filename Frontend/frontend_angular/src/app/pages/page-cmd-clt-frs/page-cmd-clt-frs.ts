import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BouttonAction} from "../../composants/boutton-action/boutton-action";
import {Pagination} from "../../composants/pagination/pagination";
import {ActivatedRoute, Router} from '@angular/router';
import { CommandeClientResponseDto, CommandeFournisseurResponseDto } from '../../../gs-api/src';

@Component({
  selector: 'app-page-cmd-clt-frs',
  imports: [
    CommonModule,
    BouttonAction,
    Pagination
  ],
  templateUrl: './page-cmd-clt-frs.html',
  styleUrl: './page-cmd-clt-frs.css'
})
export class PageCmdCltFrs implements OnInit {
  origin = '';
  commandes: (CommandeClientResponseDto | CommandeFournisseurResponseDto)[] = [];
  errorMsg = '';
  showDeleteModal = false;
  commandeToDelete: any = null;
  
  constructor(
    private router: Router,
    private activateRoute: ActivatedRoute
  ) {}

  ngOnInit(): void{
    this.activateRoute.data.subscribe(data =>{
      this.origin = data['origin']
    });
    this.loadCommandes();
  }

  loadCommandes(): void {
    // TODO: Implement real data loading from service
    this.commandes = [
      {
        id: 1,
        code: 'CMD001',
        dateCommande: '2024-01-15'
      } as any,
      {
        id: 2,
        code: 'CMD002', 
        dateCommande: '2024-01-16'
      } as any
    ];
  }

  nouvelleCommande():void {
    if(this.origin === 'client'){
      this.router.navigate(['nouvellecommandeclt'])
    }else {
      this.router.navigate(['nouvellecommandefrs'])
    }
  }

  modifierCommande(commande: any): void {
    // Navigate to edit command page with command ID
    if (commande.id) {
      this.router.navigate(['nouvellecommande', commande.id]);
    }
  }

  supprimerCommande(commande: any): void {
    this.commandeToDelete = commande;
    this.showDeleteModal = true;
  }

  confirmerSuppression(): void {
    if (this.commandeToDelete) {
      // TODO: Implement delete command logic with proper service call
      console.log('Supprimer commande:', this.commandeToDelete);
      // Example: this.commandeService.delete(this.commandeToDelete.id).subscribe(...)
      this.showDeleteModal = false;
      this.commandeToDelete = null;
    }
  }

  annulerSuppression(): void {
    this.showDeleteModal = false;
    this.commandeToDelete = null;
  }

  voirDetails(commande: CommandeClientResponseDto | CommandeFournisseurResponseDto): void {
    console.log('Voir détails commande:', commande);
  }

  getTotalCommande(commande: CommandeClientResponseDto | CommandeFournisseurResponseDto): number {
    // TODO: Calculate real total from ligne commandes
    return Math.random() * 1000;
  }

  getTotalGeneral(): number {
    return this.commandes.reduce((total, cmd) => total + this.getTotalCommande(cmd), 0);
  }

  getClientFournisseurName(commande: any): string {
    if (this.origin === 'client') {
      return 'Client Demo';
    } else {
      return 'Fournisseur Demo';
    }
  }

  getNbArticles(commande: any): number {
    return Math.floor(Math.random() * 5) + 1;
  }

  getOrderGradient(index: number): string {
    if (this.origin === 'client') {
      const gradients = [
        'bg-gradient-to-br from-blue-500/90 to-indigo-600/90',
        'bg-gradient-to-br from-cyan-500/90 to-blue-600/90',
        'bg-gradient-to-br from-indigo-500/90 to-purple-600/90'
      ];
      return gradients[index % gradients.length];
    } else {
      const gradients = [
        'bg-gradient-to-br from-orange-500/90 to-red-600/90',
        'bg-gradient-to-br from-yellow-500/90 to-orange-600/90',
        'bg-gradient-to-br from-red-500/90 to-pink-600/90'
      ];
      return gradients[index % gradients.length];
    }
  }

  getOrderIconBg(index: number): string {
    if (this.origin === 'client') {
      const backgrounds = [
        'bg-gradient-to-r from-blue-500 to-indigo-600',
        'bg-gradient-to-r from-cyan-500 to-blue-600',
        'bg-gradient-to-r from-indigo-500 to-purple-600'
      ];
      return backgrounds[index % backgrounds.length];
    } else {
      const backgrounds = [
        'bg-gradient-to-r from-orange-500 to-red-600',
        'bg-gradient-to-r from-yellow-500 to-orange-600',
        'bg-gradient-to-r from-red-500 to-pink-600'
      ];
      return backgrounds[index % backgrounds.length];
    }
  }

  getStatusBadge(index: number): string {
    const statuses = [
      'bg-green-100 text-green-800',
      'bg-yellow-100 text-yellow-800',
      'bg-blue-100 text-blue-800',
      'bg-red-100 text-red-800'
    ];
    return statuses[index % statuses.length];
  }

  getProgressBarColor(index: number): string {
    if (this.origin === 'client') {
      const colors = [
        'bg-gradient-to-r from-blue-400 to-indigo-500',
        'bg-gradient-to-r from-cyan-400 to-blue-500',
        'bg-gradient-to-r from-indigo-400 to-purple-500'
      ];
      return colors[index % colors.length];
    } else {
      const colors = [
        'bg-gradient-to-r from-orange-400 to-red-500',
        'bg-gradient-to-r from-yellow-400 to-orange-500',
        'bg-gradient-to-r from-red-400 to-pink-500'
      ];
      return colors[index % colors.length];
    }
  }

  getRandomStatus(): string {
    const statuses = ['Validée', 'En attente', 'Expédiée', 'Livrée'];
    return statuses[Math.floor(Math.random() * statuses.length)];
  }

  getRandomProgress(): number {
    return Math.floor(Math.random() * 40) + 60;
  }

  getRandomDeliveryDays(): number {
    return Math.floor(Math.random() * 10) + 2;
  }

  getValidatedCount(): number {
    return Math.floor(this.commandes.length * 0.8);
  }

  getPendingCount(): number {
    return this.commandes.length - this.getValidatedCount();
  }
}
