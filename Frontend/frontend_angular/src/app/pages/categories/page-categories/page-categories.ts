import { Component } from '@angular/core';
import {BouttonAction} from "../../../composants/boutton-action/boutton-action";
import {Pagination} from "../../../composants/pagination/pagination";
import {Router} from '@angular/router';
import {CategorieResponseDto} from '../../../../gs-api/src';
import {Category} from '../../../services/category/category';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {ModalConfirmation} from '../../../composants/modal-confirmation/modal-confirmation';

@Component({
  selector: 'app-page-categories',
  imports: [
    BouttonAction,
    Pagination,
    NgForOf,
    NgIf,
    NgClass,
    ModalConfirmation
  ],
  templateUrl: './page-categories.html',
  styleUrl: './page-categories.css'
})
export class PageCategories {

   listCategories: Array<CategorieResponseDto> = []
  selectedCatIdToDelete: number | undefined =-1
  errorMsg =''
  categoryResponseDto: CategorieResponseDto={}
  showDeleteModal = false
  categoryToDelete: CategorieResponseDto | null = null
  constructor(
    private router: Router,
    private categoryService: Category
  ) {
  }

  ngOnInit(): void {
    this.findAllCategories()
  }

  findAllCategories(): void{
    this.categoryService.findAll()
      .subscribe({
        next: (res) => {
          this.listCategories = res
        }
      })
  }
  nouvelCategory(): void {
    this.router.navigate(['nouvellecategorie'])
  }

  modifierCategory(id?: number | undefined): void {
    this.router.navigate(['nouvellecategorie', id])
  }

  supprimerCategorie(id: number | undefined): void {
    if (id) {
      const category = this.listCategories.find(c => c.id === id);
      if (category) {
        this.categoryToDelete = category;
        this.showDeleteModal = true;
      }
    }
  }

  confirmerSuppression(): void {
    if (this.categoryToDelete?.id) {
      this.categoryService.delete(this.categoryToDelete.id)
        .subscribe({
          next: () => {
            this.findAllCategories();
            this.fermerModal();
          },
          error: (err: any) => {
            this.errorMsg = err.error.message;
            this.fermerModal();
          }
        });
    }
  }

  fermerModal(): void {
    this.showDeleteModal = false;
    this.categoryToDelete = null;
  }

  getDeleteMessage(): string {
    return `Êtes-vous sûr de vouloir supprimer la catégorie "${this.categoryToDelete?.designation || ''}" ?`;
  }

  annulerSuppressionCat(): void {
    this.selectedCatIdToDelete = -1
  }

  selectCategoriePourSupprimer(id?: number | undefined): void {
      this.selectedCatIdToDelete = id
  }

  voirDetails(cat: CategorieResponseDto): void {
    console.log('Voir détails catégorie:', cat);
  }

  getCategoryGradient(index: number): string {
    const gradients = [
      'bg-gradient-to-br from-purple-500/90 to-pink-600/90',
      'bg-gradient-to-br from-blue-500/90 to-indigo-600/90',
      'bg-gradient-to-br from-green-500/90 to-emerald-600/90',
      'bg-gradient-to-br from-orange-500/90 to-red-600/90',
      'bg-gradient-to-br from-teal-500/90 to-cyan-600/90',
      'bg-gradient-to-br from-rose-500/90 to-pink-600/90'
    ];
    return gradients[index % gradients.length];
  }

  getCategoryIconBg(index: number): string {
    const backgrounds = [
      'bg-gradient-to-r from-purple-500 to-pink-600',
      'bg-gradient-to-r from-blue-500 to-indigo-600',
      'bg-gradient-to-r from-green-500 to-emerald-600',
      'bg-gradient-to-r from-orange-500 to-red-600',
      'bg-gradient-to-r from-teal-500 to-cyan-600',
      'bg-gradient-to-r from-rose-500 to-pink-600'
    ];
    return backgrounds[index % backgrounds.length];
  }

  getCategoryIcon(index: number): string {
    const icons = [
      'fas fa-laptop',
      'fas fa-tshirt',
      'fas fa-home',
      'fas fa-gamepad',
      'fas fa-book',
      'fas fa-car'
    ];
    return icons[index % icons.length];
  }

  getCategoryBadgeBg(index: number): string {
    const badges = [
      'bg-purple-500',
      'bg-blue-500',
      'bg-green-500',
      'bg-orange-500',
      'bg-teal-500',
      'bg-rose-500'
    ];
    return badges[index % badges.length];
  }

  getCategoryProgressBg(index: number): string {
    const progressBars = [
      'bg-gradient-to-r from-purple-400 to-pink-500',
      'bg-gradient-to-r from-blue-400 to-indigo-500',
      'bg-gradient-to-r from-green-400 to-emerald-500',
      'bg-gradient-to-r from-orange-400 to-red-500',
      'bg-gradient-to-r from-teal-400 to-cyan-500',
      'bg-gradient-to-r from-rose-400 to-pink-500'
    ];
    return progressBars[index % progressBars.length];
  }

  getRandomProductCount(): number {
    return Math.floor(Math.random() * 100) + 10;
  }

  getRandomRevenue(): number {
    return Math.floor(Math.random() * 500) + 50;
  }

  getRandomPerformance(): number {
    return Math.floor(Math.random() * 40) + 60;
  }

  getRandomDate(): string {
    const dates = ['12/01/2024', '15/02/2024', '08/03/2024', '22/04/2024', '05/05/2024'];
    return dates[Math.floor(Math.random() * dates.length)];
  }
}
